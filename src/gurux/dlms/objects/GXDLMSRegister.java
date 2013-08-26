//
// --------------------------------------------------------------------------
//  Gurux Ltd
// 
//
//
// Filename:        $HeadURL$
//
// Version:         $Revision$,
//                  $Date$
//                  $Author$
//
// Copyright (c) Gurux Ltd
//
//---------------------------------------------------------------------------
//
//  DESCRIPTION
//
// This file is a part of Gurux Device Framework.
//
// Gurux Device Framework is Open Source software; you can redistribute it
// and/or modify it under the terms of the GNU General Public License 
// as published by the Free Software Foundation; version 2 of the License.
// Gurux Device Framework is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of 
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
// See the GNU General Public License for more details.
//
// More information of Gurux products: http://www.gurux.org
//
// This code is licensed under the GNU General Public License v2. 
// Full text may be retrieved at http://www.gnu.org/licenses/gpl-2.0.txt
//---------------------------------------------------------------------------

package gurux.dlms.objects;

import gurux.dlms.GXDLMSClient;
import gurux.dlms.enums.ObjectType;
import gurux.dlms.enums.Unit;
import gurux.dlms.enums.DataType;
import gurux.dlms.internal.GXCommon;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GXDLMSRegister extends GXDLMSObject implements IGXDLMSBase
{
    protected int m_Scaler;
    protected int m_Unit;
    private Object m_Value;

    /**  
     Constructor.
    */
    public GXDLMSRegister()
    {
        super(ObjectType.REGISTER);
        setScaler(1);
        setUnit(Unit.NONE);
    }

public GXDLMSRegister(ObjectType type, String ln, int sn)
{
    super(type, ln, sn);
    setScaler(1);
    setUnit(Unit.NONE);
}

    /**  
     Constructor.

     @param ln Logican Name of the object.
    */
    public GXDLMSRegister(String ln)
    {
        this(ObjectType.REGISTER, ln, 0);
    }

    /**  
     Constructor.

     @param ln Logican Name of the object.
     @param sn Short Name of the object.
    */
    public GXDLMSRegister(String ln, int sn)
    {
        this(ObjectType.REGISTER, ln, 0);
    }

    /** 
     Scaler of COSEM Register object.
    */
    public final double getScaler()
    {        
        return Math.pow(10, m_Scaler);
    }
    public final void setScaler(double value)
    {
        m_Scaler = (int) Math.log10(value);
    }

    /** 
     Unit of COSEM Register object.
    */
    public final Unit getUnit()
    {        
        return Unit.forValue(m_Unit);
    }
    public final void setUnit(Unit value)
    {
        m_Unit = value.getValue();
    }

    /** 
     Value of COSEM Register object.
     Register value is not serialized because XML serializer can't handle all cases.
    */
    public final Object getValue()
    {
        return m_Value;
    }
    public final void setValue(Object value)
    {
        m_Value = value;
    }   
    
    /*
     * Reset value.
     */
    public byte[][] reset(GXDLMSClient client)
    {
        byte[] ret = client.method(getName(), getObjectType(), 1, (int) 0);
        return new byte[][]{ret};    
    }

    @Override
    public Object[] getValues()
    {        
        String str = String.format("Scaler: %1$,.2f Unit: ", getScaler());
        str += getUnit().toString();
        return new Object[] {getLogicalName(), getValue(), str};
    }
   
    @Override
    public void invoke(int index, Object parameters)
    {
        // Resets the value to the default value. 
        // The default value is an instance specific constant.
        if (index == 1)
        {
            setValue(null);
        }
        else
        {
            throw new IllegalArgumentException("Invoke failed. Invalid attribute index.");
        }
    }
    
    /*
     * Is attribute read. This can be used with static attributes to make 
     * meter reading faster.
     */    
    @Override
    public boolean isRead(int index)
    {
        if (index == 3)
        {
            return m_Unit != 0;
        }
        return super.isRead(index);
    }

    /*
     * Returns collection of attributes to read.
     * 
     * If attribute is static and already read or device is returned HW error it is not returned.
     */
    @Override
    public int[] GetAttributeIndexToRead()
    {
        java.util.ArrayList<Integer> attributes = new java.util.ArrayList<Integer>();
        //LN is static and read only once.
        if (LogicalName == null || LogicalName.compareTo("") == 0)
        {
            attributes.add(1);
        }
        //ScalerUnit
        if (!isRead(3))
        {
            attributes.add(3);
        }
        //Value
        if (canRead(2))
        {
            attributes.add(2);
        }          
        return toIntArray(attributes);
    }
    
    /*
     * Returns amount of attributes.
     */    
    @Override
    public int getAttributeCount()
    {
        return 3;
    }
    
    /*
     * Returns amount of methods.
     */ 
    @Override
    public int getMethodCount()
    {
        return 1;
    }
    
    /*
     * Returns value of given attribute.
     */    
    @Override
    public Object getValue(int index, DataType[] type, byte[] parameters, boolean raw)
    {
        if (index == 1)
        {
            type[0] = DataType.OCTET_STRING;
            return getLogicalName();
        }
        if (index == 2)
        {
            return getValue();
        }
        if (index == 3)
        {
            try 
            {
                type[0] = DataType.ARRAY;
                ByteArrayOutputStream data = new ByteArrayOutputStream();
                data.write(DataType.STRUCTURE.getValue());
                data.write(2);
                GXCommon.setData(data, DataType.UINT8, m_Scaler);            
                GXCommon.setData(data, DataType.UINT8, m_Unit);
                return data.toByteArray();
            }
            catch (Exception ex) 
            {
                Logger.getLogger(GXDLMSRegister.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex.getMessage());
            }
        }
        throw new IllegalArgumentException("GetValue failed. Invalid attribute index.");
    }
    
    /*
     * Set value of given attribute.
     */
    @Override
    public void setValue(int index, Object value, boolean raw)
    {
        if (index == 1)
        {
            setLogicalName(GXDLMSObject.toLogicalName((byte[]) value));            
        }
        else if (index == 2)
        {
            if (!raw)
            {
                if (m_Scaler != 0)
                {
                    try
                    {                        
                        m_Value = ((Number)value).doubleValue() * getScaler();
                    }
                    catch (Exception ex)
                    {
                        //Sometimes scaler is set for wrong Object type.
                        setValue(value);
                    }
                }
                else
                {
                    setValue(value);
                }
            }
            else
            {
                setValue(value);
            }            
        }
        else if (index == 3)
        {            
            //Set default values.
            if (value == null)
            {
                m_Scaler = m_Unit = 0;
            }
            else
            {
                if (Array.getLength(value) != 2)
                {
                    m_Scaler = m_Unit = 0;
                }
                else
                {
                    m_Scaler = ((Number)Array.get(value, 0)).intValue();
                    m_Unit = (((Number)Array.get(value, 1)).intValue() & 0xFF);
                }
            }
        }
        else
        {
            throw new IllegalArgumentException("GetValue failed. Invalid attribute index.");
        }
    }       
}