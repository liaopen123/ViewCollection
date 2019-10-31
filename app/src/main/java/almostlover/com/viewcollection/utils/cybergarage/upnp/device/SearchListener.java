/******************************************************************
*
*	CyberUPnP for Java
*
*	Copyright (C) Satoshi Konno 2002
*
*	File: SearchListener.java
*
*	Revision;
*
*	11/18/02b
*		- first revision.
*	
******************************************************************/

package almostlover.com.viewcollection.utils.cybergarage.upnp.device;

import almostlover.com.viewcollection.utils.cybergarage.upnp.ssdp.*;

public interface SearchListener
{
	public void deviceSearchReceived(SSDPPacket ssdpPacket);
}
