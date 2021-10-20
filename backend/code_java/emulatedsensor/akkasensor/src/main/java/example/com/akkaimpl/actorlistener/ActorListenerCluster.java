package example.com.akkaimpl.actorlistener;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.cluster.ClusterEvent;

public final class ActorListenerCluster extends MessageActorListener{
    private ActorListenerCluster(final ActorContext<MessageActorListener.Event> context) {
        super(context);
    }

    public static Behavior<MessageActorListener.Event> getInstance(){
        return Behaviors.setup(ctx -> new ActorListenerCluster(ctx).create(ctx));
    }

    @Override
    protected Behavior<Event> memberChangeNode(ActorContext<Event> ctx, MemberChange memberChange) {
        if (memberChange.memberEvent() instanceof ClusterEvent.MemberUp member) {
            ctx.getLog().info("Member is up: {}", member.member());
        } else if (memberChange.memberEvent() instanceof ClusterEvent.MemberRemoved member) {
            ctx.getLog().info("Member is removed: {} after {}", member.member(), member.previousStatus()
            );
        }
        return Behaviors.same();
    }

    @Override
    protected Behavior<Event> reachabilityNode(ActorContext<Event> ctx, ReachabilityChange reachabilityChange) {
        if (reachabilityChange.reachabilityEvent() instanceof ClusterEvent.UnreachableMember member) {
            ctx.getLog().info("Member detected as unreachable: {}", member.member());
        } else if (reachabilityChange.reachabilityEvent() instanceof ClusterEvent.ReachableMember member) {
            ctx.getLog().info("Member back to reachable: {}", member.member());
        }
        return Behaviors.same();
    }
}
