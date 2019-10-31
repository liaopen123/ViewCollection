/******************************************************************
*
*	CyberUPnP for Java
*
*	Copyright (C) Satoshi Konno 2002
*
*	File: ActionListener.java
*
*	Revision;
*
*	01/16/03
*		- first revision.
*	
******************************************************************/

package almostlover.com.viewcollection.utils.cybergarage.upnp.control;


import almostlover.com.viewcollection.utils.cybergarage.upnp.Action;

public interface ActionListener
{
	public boolean actionControlReceived(Action action);
}
