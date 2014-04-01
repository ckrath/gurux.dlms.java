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

package gurux.dlms.enums;

/**
 *
 * Defines the mode controlling the auto dial functionality concerning the timing.
 */
public enum AutoConnectMode 
{
    /*
     * No auto dialling,
     */
    NO_AUTO_DIALLING(0),
    /**
     * Auto dialling allowed anytime,
     */
    AUTO_DIALLING_ALLOWED_ANYTIME(1),
    /**
     * Auto dialling allowed within the validity time of the calling window.
     */
    AUTO_DIALLING_ALLOWED_CALLING_WINDOW(2),
    /**
     * “regular” auto dialling allowed within the validity time
     * of the calling window; “alarm” initiated auto dialling allowed anytime,
     */
    REGULAR_AUTO_DIALLING_ALLOWED_CALLING_WINDOW(3),
    /**
     * SMS sending via Public Land Mobile Network (PLMN),
     */
    SMS_SENDING_PLMN(4),
    /*
     * SMS sending via PSTN.
     */
    SMS_SENDING_PSTN(5),
    /*
     * Email sending.
     */
    EMAIL_SENDING(6),
    MANUFACTURE_SPESIFIC(200);
    //(200..255) manufacturer specific modes
    
    private int value;
    private static java.util.HashMap<Integer, AutoConnectMode> mappings;
    private static java.util.HashMap<Integer, AutoConnectMode> getMappings()
    {
        synchronized (AutoConnectMode.class)
        {
            if (mappings == null)
            {
                mappings = new java.util.HashMap<Integer, AutoConnectMode>();
            }
        }
        return mappings;
    }

    /*
     * Constructor.
     */
    @SuppressWarnings("LeakingThisInConstructor")
    private AutoConnectMode(int value)
    {
        this.value = value;
        if (value > 200 && value < 255)
        {
            value = 200;
        }
        getMappings().put(value, this);
    }

    /*
     * Get integer value for enum.
     */
    public int getValue()
    {
        return value;
    }
    
    /*
     * Convert integer for enum value.
     */
    public static AutoConnectMode forValue(int value)
    {
        if (value > 200 && value < 255)
        {
            return MANUFACTURE_SPESIFIC;
        }
        return getMappings().get(value);
    }
}
