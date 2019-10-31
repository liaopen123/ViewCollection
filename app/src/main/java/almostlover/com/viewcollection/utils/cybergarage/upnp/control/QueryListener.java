/******************************************************************
*
*	CyberUPnP for Java
*
*	Copyright (C) Satoshi Konno 2002-2003
*
*	File: QueryListener.java
*
*	Revision;
*
*	01/30/03
*		- first revision.
*	01/04/04
*		- Changed the interface.
*	
******************************************************************/

package almostlover.com.viewcollection.utils.cybergarage.upnp.control;

import almostlover.com.viewcollection.utils.cybergarage.upnp.*;

public interface QueryListener
{
	public boolean queryControlReceived(StateVariable stateVar);
}
