package example.com.akkaimpl.actorlistener;

import akka.actor.typed.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.cluster.ClusterEvent;
import akka.cluster.typed.Cluster;
import akka.cluster.typed.Subscribe;

public abstract class MessageActorListener {

    protected MessageActorListener(ActorContext<Event> ctx){
        Cluster cluster = Cluster.get(ctx.getSystem());
        setupMemberEventAdapter(ctx,cluster);
        setupReachabilityAdapter(ctx,cluster);
    }
    protected interface Event{}
    protected static record ReachabilityChange(ClusterEvent.ReachabilityEvent reachabilityEvent) implements Event {}
    protected static record MemberChange(ClusterEvent.MemberEvent memberEvent) implements Event {}

    private static void setupMemberEventAdapter(ActorContext<MessageActorListener.Event> context, Cluster cluster){
        ActorRef<ClusterEvent.MemberEvent> memberEventAdapter =
                context.messageAdapter(ClusterEvent.MemberEvent.class, MessageActorListener.MemberChange::new);
        cluster.subscriptions().tell(Subscribe.create(memberEventAdapter, ClusterEvent.MemberEvent.class));
    }

    private static void setupReachabilityAdapter(ActorContext<MessageActorListener.Event> context, Cluster cluster){
        ActorRef<ClusterEvent.ReachabilityEvent> reachabilityAdapter =
                context.messageAdapter(ClusterEvent.ReachabilityEvent.class, MessageActorListener.ReachabilityChange::new);
        cluster.subscriptions().tell(Subscribe.create(reachabilityAdapter, ClusterEvent.ReachabilityEvent.class));
    }

    protected Behavior<Event> create(final ActorContext<Event> ctx){
        return Behaviors.receive(Event.class)
                .onMessage(ReachabilityChange.class, reachability-> reachabilityNode(ctx,reachability))
                .onMessage(MemberChange.class, memberChange ->memberChangeNode(ctx, memberChange))
                .build();
    }

    protected abstract Behavior<Event> memberChangeNode(ActorContext<Event> ctx, MemberChange memberChange);
    protected abstract Behavior<Event> reachabilityNode(ActorContext<Event> ctx, ReachabilityChange reachabilityChange);

}
