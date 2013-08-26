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
import gurux.dlms.GXDateTime;
import gurux.dlms.enums.DataType;
import gurux.dlms.enums.ObjectType;
import gurux.dlms.internal.GXCommon;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GXDLMSActivityCalendar extends GXDLMSObject implements IGXDLMSBase
{
    private String CalendarNameActive;
    private String CalendarNamePassive;
    private GXDLMSSeasonProfile[] SeasonProfileActive;
    private GXDLMSWeekProfile[] WeekProfileTableActive;
    private GXDLMSDayProfile[] DayProfileTableActive;
    private GXDLMSSeasonProfile[] SeasonProfilePassive;    
    private GXDLMSDayProfile[] DayProfileTablePassive;
    private GXDLMSWeekProfile[] WeekProfileTablePassive;
    private GXDateTime Time;
    
    /**  
     Constructor.
    */
    public GXDLMSActivityCalendar()
    {
        this("0.0.13.0.0.255");
    }

    /**  
     Constructor.

     @param ln Logican Name of the object.
    */
    public GXDLMSActivityCalendar(String ln)
    {
        this(ln, (short) 0);
    }

    /**  
     Constructor.

     @param ln Logican Name of the object.
     @param sn Short Name of the object.
    */
    public GXDLMSActivityCalendar(String ln, int sn)
    {
        super(ObjectType.ACTIVITY_CALENDAR, ln, sn);
    }
    
    public final String getCalendarNameActive()
    {
        return CalendarNameActive;
    }
    public final void setCalendarNameActive(String value)
    {
        CalendarNameActive = value;
    }

    public final GXDLMSSeasonProfile[] getSeasonProfileActive()
    {
        return SeasonProfileActive;
    }
    public final void setSeasonProfileActive(GXDLMSSeasonProfile[] value)
    {
        SeasonProfileActive = value;
    }

    public final GXDLMSWeekProfile[] getWeekProfileTableActive()
    {
        return WeekProfileTableActive;
    }
    public final void setWeekProfileTableActive(GXDLMSWeekProfile[] value)
    {
        WeekProfileTableActive = value;
    }

    public final GXDLMSDayProfile[] getDayProfileTableActive()
    {
        return DayProfileTableActive;
    }
    public final void setDayProfileTableActive(GXDLMSDayProfile[] value)
    {
        DayProfileTableActive = value;
    }

    public final String getCalendarNamePassive()
    {
        return CalendarNamePassive;
    }
    public final void setCalendarNamePassive(String value)
    {
        CalendarNamePassive = value;
    }

    public final GXDLMSSeasonProfile[] getSeasonProfilePassive()
    {
        return SeasonProfilePassive;
}
    public final void setSeasonProfilePassive(GXDLMSSeasonProfile[] value)
    {
        SeasonProfilePassive = value;
    }

    public final GXDLMSWeekProfile[] getWeekProfileTablePassive()
    {
        return WeekProfileTablePassive;
    }
    public final void setWeekProfileTablePassive(GXDLMSWeekProfile[] value)
    {
        WeekProfileTablePassive = value;
    }

    public final GXDLMSDayProfile[] getDayProfileTablePassive()
    {
        return DayProfileTablePassive;
    }
    public final void setDayProfileTablePassive(GXDLMSDayProfile[] value)
    {
        DayProfileTablePassive = value;
    }

    public final GXDateTime getTime()
    {
        return Time;
    }
    public final void setTime(GXDateTime value)
    {
        Time = value;
    }

    @Override
    public Object[] getValues()
    {
        return new Object[] {getLogicalName(), getCalendarNameActive(), getSeasonProfileActive(), getWeekProfileTableActive(), getDayProfileTableActive(), getCalendarNamePassive(), getSeasonProfilePassive(), getWeekProfileTablePassive(), getDayProfileTablePassive(), getTime()};
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
        //CalendarNameActive
        if (canRead(2))
        {
            attributes.add(2);
        }            
        //SeasonProfileActive
        if (canRead(3))
        {
            attributes.add(3);
        } 

        //WeekProfileTableActive
        if (canRead(4))
        {
            attributes.add(4);
        } 
        //DayProfileTableActive
        if (canRead(5))
        {
            attributes.add(5);
        } 
        //CalendarNamePassive
        if (canRead(6))
        {
            attributes.add(6);
        } 
        //SeasonProfileActive
        if (canRead(7))
        {
            attributes.add(7);
        }
        //WeekProfileTableActive
        if (canRead(8))
        {
            attributes.add(8);
        }
        //DayProfileTableActive
        if (canRead(9))
        {
            attributes.add(9);
        }
        //Time.
        if (canRead(10))
        {
            attributes.add(10);
        }                
        return toIntArray(attributes);
    }
    
    /*
     * Returns amount of attributes.
     */  
    @Override
    public int getAttributeCount()
    {
        return 10;
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
            type[0] = DataType.OCTET_STRING;
            return GXDLMSClient.changeType(GXCommon.getBytes(getCalendarNameActive()), DataType.OCTET_STRING);
        }
        if (index == 3)
        {
            type[0] = DataType.ARRAY;
            ByteArrayOutputStream data = new ByteArrayOutputStream();
            data.write((byte)DataType.ARRAY.getValue());
            if (getSeasonProfileActive() == null)
            {
                //Add count            
                GXCommon.setObjectCount(0, data);
            }
            else
            {
                int cnt = getSeasonProfileActive().length;
                //Add count            
                GXCommon.setObjectCount(cnt, data);
                try
                {
                    for (GXDLMSSeasonProfile it :  getSeasonProfileActive())
                    {
                        data.write((byte)DataType.STRUCTURE.getValue());
                        data.write(3);
                        GXCommon.setData(data, DataType.OCTET_STRING, GXCommon.getBytes(it.getName()));
                        GXCommon.setData(data, DataType.OCTET_STRING, it.getStart());
                        GXCommon.setData(data, DataType.OCTET_STRING, GXCommon.getBytes(it.getWeekName()));
                    }
                }
                catch(Exception ex)
                {
                    throw new RuntimeException(ex.getMessage());           
                }
            }
            return data.toByteArray();
        }
        if (index == 4)
        {
            type[0] = DataType.ARRAY;
            ByteArrayOutputStream data = new ByteArrayOutputStream();            
            data.write((byte)DataType.ARRAY.getValue());
            if (getWeekProfileTableActive() == null)
            {
                //Add count            
                GXCommon.setObjectCount(0, data);
            }
            else
            {
                int cnt = getWeekProfileTableActive().length;
                //Add count            
                GXCommon.setObjectCount(cnt, data);
                try
                {
                    for (GXDLMSWeekProfile it : getWeekProfileTableActive())
                    {
                        data.write((byte)DataType.ARRAY.getValue());
                        data.write(8);
                        GXCommon.setData(data, DataType.OCTET_STRING, GXCommon.getBytes(it.getName()));
                        GXCommon.setData(data, DataType.UINT8, it.getMonday());
                        GXCommon.setData(data, DataType.UINT8, it.getTuesday());
                        GXCommon.setData(data, DataType.UINT8, it.getWednesday());
                        GXCommon.setData(data, DataType.UINT8, it.getThursday());
                        GXCommon.setData(data, DataType.UINT8, it.getFriday());
                        GXCommon.setData(data, DataType.UINT8, it.getSaturday());
                        GXCommon.setData(data, DataType.UINT8, it.getSunday());
                    }
                }
                catch(Exception ex)
                {
                    throw new RuntimeException(ex.getMessage());           
                }
            }
            return data.toByteArray();
        }
        if (index == 5)
        {
            type[0] = DataType.ARRAY;
            ByteArrayOutputStream data = new ByteArrayOutputStream();
            data.write((byte)DataType.ARRAY.getValue());
            if (getDayProfileTableActive() == null)
            {
                //Add count            
                GXCommon.setObjectCount(0, data);
            }
            else
            {
                int cnt = getDayProfileTableActive().length;
                //Add count            
                GXCommon.setObjectCount(cnt, data);
                try
                {
                    for (GXDLMSDayProfile it : getDayProfileTableActive())
                    {
                        data.write((byte)DataType.STRUCTURE.getValue());
                        data.write(2);
                        GXCommon.setData(data, DataType.UINT8, it.getDayId());
                        data.write((byte)DataType.ARRAY.getValue());
                        //Add count            
                        GXCommon.setObjectCount(it.getDaySchedules().length, data);                        
                        for(GXDLMSDayProfileAction action : it.getDaySchedules())
                        {
                            data.write((byte)DataType.STRUCTURE.getValue());
                            data.write(3);
                            GXCommon.setData(data, DataType.TIME, action.getStartTime());
                            GXCommon.setData(data, DataType.OCTET_STRING, action.getScriptLogicalName().getBytes("ASCII"));
                            GXCommon.setData(data, DataType.UINT16, action.getScriptSelector());
                        }
                    }
                }
                catch(Exception ex)
                {
                    throw new RuntimeException(ex.getMessage());           
                }
            }
            return data.toByteArray();
        }
        if (index == 6)
        {
            type[0] = DataType.OCTET_STRING;
            return GXDLMSClient.changeType(GXCommon.getBytes(getCalendarNamePassive()), DataType.OCTET_STRING);
        }
        //
        if (index == 7)
        {
            type[0] = DataType.ARRAY;
            ByteArrayOutputStream data = new ByteArrayOutputStream();
            data.write((byte)DataType.ARRAY.getValue());
            if (getSeasonProfileActive() == null)
            {
                //Add count            
                GXCommon.setObjectCount(0, data);
            }
            else
            {
                int cnt = getSeasonProfileActive().length;
                try
                {
                    //Add count            
                    GXCommon.setObjectCount(cnt, data);
                    for (GXDLMSSeasonProfile it : getSeasonProfileActive())
                    {
                        data.write((byte)DataType.STRUCTURE.getValue());
                        data.write(3);
                        GXCommon.setData(data, DataType.OCTET_STRING, GXCommon.getBytes(it.getName()));
                        GXCommon.setData(data, DataType.OCTET_STRING, it.getStart());
                        GXCommon.setData(data, DataType.OCTET_STRING, GXCommon.getBytes(it.getWeekName()));
                    }
                }
                catch(Exception ex)
                {
                    throw new RuntimeException(ex.getMessage());           
                }
            }
            return data.toByteArray();
        }
        if (index == 8)
        {
            type[0] = DataType.ARRAY;
            ByteArrayOutputStream data = new ByteArrayOutputStream();
            data.write((byte)DataType.ARRAY.getValue());
            if (getWeekProfileTableActive() == null)
            {
                //Add count            
                GXCommon.setObjectCount(0, data);
            }
            else
            {
                int cnt = getWeekProfileTableActive().length;
                //Add count            
                GXCommon.setObjectCount(cnt, data);
                try
                {
                    for(GXDLMSWeekProfile it : getWeekProfileTableActive())
                    {
                        data.write((byte)DataType.ARRAY.getValue());
                        data.write(8);
                        GXCommon.setData(data, DataType.OCTET_STRING, GXCommon.getBytes(it.getName()));
                        GXCommon.setData(data, DataType.UINT8, it.getMonday());
                        GXCommon.setData(data, DataType.UINT8, it.getTuesday());
                        GXCommon.setData(data, DataType.UINT8, it.getWednesday());
                        GXCommon.setData(data, DataType.UINT8, it.getThursday());
                        GXCommon.setData(data, DataType.UINT8, it.getFriday());
                        GXCommon.setData(data, DataType.UINT8, it.getSaturday());
                        GXCommon.setData(data, DataType.UINT8, it.getSunday());
                    }
                }
                catch(Exception ex)
                {
                    throw new RuntimeException(ex.getMessage());           
                }
            }
            return data.toByteArray();
        }
        if (index == 9)
        {
            type[0] = DataType.ARRAY;
            ByteArrayOutputStream data = new ByteArrayOutputStream();
            data.write((byte)DataType.ARRAY.getValue());
            if (getDayProfileTableActive() == null)
            {
                //Add count            
                GXCommon.setObjectCount(0, data);
            }
            else
            {
                int cnt = getDayProfileTableActive().length;
                //Add count            
                GXCommon.setObjectCount(cnt, data);
                try
                {
                    for(GXDLMSDayProfile it : getDayProfileTableActive())
                    {
                        data.write(DataType.STRUCTURE.getValue());
                        data.write(2);
                        GXCommon.setData(data, DataType.UINT8, it.getDayId());
                        data.write(DataType.ARRAY.getValue());
                        //Add count            
                        GXCommon.setObjectCount(it.getDaySchedules().length, data);
                        for(GXDLMSDayProfileAction action : it.getDaySchedules())
                        {
                            data.write(DataType.STRUCTURE.getValue());
                            data.write(3);
                            GXCommon.setData(data, DataType.TIME, action.getStartTime());
                            GXCommon.setData(data, DataType.OCTET_STRING, action.getScriptLogicalName().getBytes("ASCII"));
                            GXCommon.setData(data, DataType.UINT16, action.getScriptSelector());
                        }
                    }
                }
                catch(Exception ex)
                {
                    throw new RuntimeException(ex.getMessage());           
                }
            }
            return data.toByteArray();
        }
        if (index == 10)
        {
            type[0] = DataType.DATETIME;
            return getTime();
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
            setCalendarNameActive(GXDLMSClient.changeType((byte[])value, DataType.STRING).toString());
        }
        else if (index == 3)
        {
            setSeasonProfileActive(null);
            if (value != null)
            {
                List<GXDLMSSeasonProfile> items = new ArrayList<GXDLMSSeasonProfile>();
                for(Object item : (Object[])value)
                {
                    GXDLMSSeasonProfile it = new GXDLMSSeasonProfile();
                    it.setName(GXDLMSClient.changeType((byte[]) Array.get(item, 0), DataType.STRING).toString());
                    it.setStart((GXDateTime) GXDLMSClient.changeType((byte[])Array.get(item, 1), DataType.DATETIME));
                    it.setWeekName(GXDLMSClient.changeType((byte[]) Array.get(item, 2), DataType.STRING).toString());
                    items.add(it);
                }
                setSeasonProfileActive(items.toArray(new GXDLMSSeasonProfile[items.size()]));
            }
        }
        else if (index == 4)
        {
            setWeekProfileTableActive(null);
            if (value != null)
            {
                List<GXDLMSWeekProfile> items = new ArrayList<GXDLMSWeekProfile>();
                for (Object item : (Object[]) value)
                {
                    GXDLMSWeekProfile it = new GXDLMSWeekProfile();
                    it.setName(GXDLMSClient.changeType((byte[])Array.get(item, 0), DataType.STRING).toString());
                    it.setMonday(((Number) Array.get(item, 1)).intValue());
                    it.setTuesday(((Number) Array.get(item, 2)).intValue());
                    it.setWednesday(((Number) Array.get(item, 3)).intValue());
                    it.setThursday(((Number) Array.get(item, 4)).intValue());
                    it.setFriday(((Number) Array.get(item, 5)).intValue());
                    it.setSaturday(((Number) Array.get(item, 6)).intValue());
                    it.setSunday(((Number) Array.get(item, 7)).intValue());
                    items.add(it);
                }
                setWeekProfileTableActive(items.toArray(new GXDLMSWeekProfile[items.size()]));
            }
        }
        else if (index == 5)
        {
            setDayProfileTableActive(null);
            if (value != null)
            {
                List<GXDLMSDayProfile> items = new ArrayList<GXDLMSDayProfile>();
                for (Object item : (Object[])value)
                {
                    GXDLMSDayProfile it = new GXDLMSDayProfile();
                    it.setDayId(((Number) Array.get(item, 0)).intValue());
                    List<GXDLMSDayProfileAction> actions = new ArrayList<GXDLMSDayProfileAction>();
                    for (Object it2 : (Object[])Array.get(item, 1))
                    {
                        GXDLMSDayProfileAction ac = new GXDLMSDayProfileAction();
                        ac.setStartTime((GXDateTime)GXDLMSClient.changeType((byte[])Array.get(it2, 0), DataType.TIME));
                        ac.setScriptLogicalName(GXDLMSClient.changeType((byte[])Array.get(it2, 1), DataType.STRING).toString());
                        ac.setScriptSelector(((Number)Array.get(it2, 2)).intValue());
                        actions.add(ac);
                    }
                    it.setDaySchedules(actions.toArray(new GXDLMSDayProfileAction[actions.size()]));
                    items.add(it);
                }
                setDayProfileTableActive(items.toArray(new GXDLMSDayProfile[items.size()]));
            }
        }                
        else if (index == 6)
        {
            setCalendarNamePassive(GXDLMSClient.changeType((byte[])value, DataType.STRING).toString());
        }
        else if (index == 7)
        {
            setSeasonProfilePassive(null);
            if (value != null)
            {
                List<GXDLMSSeasonProfile> items = new ArrayList<GXDLMSSeasonProfile>();
                for(Object item : (Object[])value)
                {
                    GXDLMSSeasonProfile it = new GXDLMSSeasonProfile();
                    it.setName(GXDLMSClient.changeType((byte[])Array.get(item, 0), DataType.STRING).toString());
                    it.setStart((GXDateTime)GXDLMSClient.changeType((byte[])Array.get(item, 1), DataType.DATETIME));
                    it.setWeekName(GXDLMSClient.changeType((byte[])Array.get(item, 2), DataType.STRING).toString());
                    items.add(it);
                }
                setSeasonProfilePassive(items.toArray(new GXDLMSSeasonProfile[items.size()]));
            }
        }
        else if (index == 8)
        {
            setWeekProfileTablePassive(null);
            if (value != null)
            {
                List<GXDLMSWeekProfile> items = new ArrayList<GXDLMSWeekProfile>();
                for (Object item : (Object[])value)
                {
                    GXDLMSWeekProfile it = new GXDLMSWeekProfile();
                    it.setName(GXDLMSClient.changeType((byte[])Array.get(item, 0), DataType.STRING).toString());
                    it.setMonday(((Number) Array.get(item, 1)).intValue());
                    it.setTuesday(((Number) Array.get(item, 2)).intValue());
                    it.setWednesday(((Number) Array.get(item, 3)).intValue());
                    it.setThursday(((Number) Array.get(item, 4)).intValue());
                    it.setFriday(((Number) Array.get(item, 5)).intValue());
                    it.setSaturday(((Number) Array.get(item, 6)).intValue());
                    it.setSunday(((Number) Array.get(item, 7)).intValue());
                    items.add(it);
                }
                setWeekProfileTablePassive(items.toArray(new GXDLMSWeekProfile[items.size()]));
            }
        }
        else if (index == 9)
        {
            setDayProfileTablePassive(null);
            if (value != null)
            {
                List<GXDLMSDayProfile> items = new ArrayList<GXDLMSDayProfile>();
                for (Object item : (Object[])value)
                {
                    GXDLMSDayProfile it = new GXDLMSDayProfile();
                    it.setDayId(((Number) Array.get(item, 0)).intValue());
                    List<GXDLMSDayProfileAction> actions = new ArrayList<GXDLMSDayProfileAction>();
                    for (Object it2 :  (Object[])Array.get(item, 1))
                    {
                        GXDLMSDayProfileAction ac = new GXDLMSDayProfileAction();
                        ac.setStartTime((GXDateTime)GXDLMSClient.changeType((byte[])Array.get(it2, 0), DataType.TIME));
                        ac.setScriptLogicalName(GXDLMSClient.changeType((byte[])Array.get(it2, 1), DataType.STRING).toString());
                        ac.setScriptSelector(((Number)Array.get(it2, 2)).intValue());
                        actions.add(ac);
                    }
                    it.setDaySchedules(actions.toArray(new GXDLMSDayProfileAction[actions.size()]));
                    items.add(it);
                }
                setDayProfileTablePassive(items.toArray(new GXDLMSDayProfile[items.size()]));
            }
        }
        else if (index == 10)
        {
            setTime((GXDateTime)GXDLMSClient.changeType((byte[])value, DataType.DATETIME));
        }
        else
        {
            throw new IllegalArgumentException("GetValue failed. Invalid attribute index.");
        }
    }
}