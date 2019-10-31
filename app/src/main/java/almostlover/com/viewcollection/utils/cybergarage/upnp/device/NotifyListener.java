/******************************************************************
*
*	CyberUPnP for Java
*
*	Copyright (C) Satoshi Konno 2002
*
*	File: DeviceNotifyListener.java
*
*	Revision;
*
*	11/18/02
*		- first revision.
*	
******************************************************************/

package almostlover.com.viewcollection.utils.cybergarage.upnp.device;

import almostlover.com.viewcollection.utils.cybergarage.upnp.ssdp.*;

public interface NotifyListener
{
	public void deviceNotifyReceived(SSDPPacket ssdpPacket);
}
