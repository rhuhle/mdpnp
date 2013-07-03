package org.mdpnp.apps.testapp;

import ice.DeviceConnectivity;
import ice.DeviceIdentity;

import java.lang.ref.SoftReference;

/**
 * Convenience class for storing DeviceIdentity and DeviceConnectivity instances
 * DeviceIdentity is required, DeviceConnectivity is only relevant for "connected" devices
 * and may be null.  A softreference to a DeviceIcon constructed from the DeviceIdentity raster
 * is also maintained (and reconstructed on demand). 
 * @author jplourde
 *
 */
public class Device {
    private final DeviceIdentity deviceIdentity = new DeviceIdentity();
    private DeviceConnectivity deviceConnectivity;
    
    private SoftReference<DeviceIcon> realIcon;
    
    public Device() {
        
    }
    
    public DeviceIcon getIcon() {
        DeviceIcon di = null;
        if(null != realIcon) {
            di = realIcon.get();
        }
        if(null == di) {
            di = new DeviceIcon(deviceIdentity.icon);
            realIcon = new SoftReference<DeviceIcon>(di);
        }
        return di;
    }
    
    public String getMakeAndModel() {
        // TODO cache this
        return deviceIdentity.manufacturer + " " + deviceIdentity.model;
    }
    
    public Device(DeviceIdentity di) {
        deviceIdentity.copy_from(di);
    }
    
    public DeviceIdentity getDeviceIdentity() {
        return deviceIdentity;
    }
    
    public DeviceConnectivity getDeviceConnectivity() {
        return deviceConnectivity;
    }
    
    public void setDeviceConnectivity(DeviceConnectivity deviceConnectivity) {
        if(null == deviceConnectivity) {
            this.deviceConnectivity = null;
        } else {
            if(null == this.deviceConnectivity) {
                this.deviceConnectivity = new DeviceConnectivity(deviceConnectivity);
            } else {
                this.deviceConnectivity.copy_from(deviceConnectivity);
            }
        }
    }
}
