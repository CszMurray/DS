package message;

import node.Node;
import node.NodeInterface;

public abstract class Broadcast implements MessageInterface {

	// TODO different broadcasts perform different actions
	// What would be good: given a type we can infer the actions which should be taken before and after a broadcast
	// Basically we inject the actions to be performed into the before and after actions
//	private BroadcastType type;
	
	// TODO Probably don't need this as we don't really care where the message was sent from
//	private NodeInterface fromNode;
	
	public Broadcast() {
		// TODO Not sure if we need this yet
//		this.type = type;
	}
	
	@Override
	public void performAction(Node node) {

		// Perform an action on the node before we broadcast the message to the children
		performActionBeforeBroadcast(node);
		
		// If there are child nodes broadcast the message
		if (!node.getMstChildNodes().isEmpty()){
			this.send(node);
		} else {
			performActionAfterBroadcast(node);
		}
	}
	
	@Override
	public void send(Node node) {
		// TODO Not sure if we need this yet
//		this.fromNode = node;

		// Broadcast a message to all of this node's MST children
		for (NodeInterface n : node.getMstChildNodes().values()){
			n.getNodeThread().addMessageToNodeQueue(this);
		}
	}

	/**
	 * Perform an action before the broadcast message is forwarded on to its children
	 * @param node the current node which is to perform the action
	 */
	public abstract void performActionBeforeBroadcast(Node node);
	
	/**
	 * Perform this action on the nodes which have no children (leaf nodes)
	 * @param node the current node which is to perform the action
	 */
	public abstract void performActionAfterBroadcast(Node node);
}