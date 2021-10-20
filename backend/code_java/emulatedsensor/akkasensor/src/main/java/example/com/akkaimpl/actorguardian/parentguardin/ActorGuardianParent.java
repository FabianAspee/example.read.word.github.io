package example.com.akkaimpl.actorguardian.parentguardin;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import example.com.akkaimpl.actorguardian.childguardian.ActorGuardianChild;
import example.com.akkaimpl.actorguardian.childguardian.MessageActorGuardianChild.InitGuardian;
import example.com.akkaimpl.configproperties.ApplicationProperties;

import java.util.Optional;
import java.util.stream.IntStream;

public class ActorGuardianParent extends MessageActorGuardianParent {
    private ActorGuardianParent(){
        super();
    }
    public static Behavior<CommandGuardianParent> getInstance(){
        return Behaviors.setup(ctx -> new ActorGuardianParent().createMsg(ctx));
    }


    @Override
    public CreateSensor getInstance(LifeCycleActor message) {
        if(message instanceof CreateSensor guardian){
            return guardian;
        }
        return null;
    }

    @Override
    public <T extends LifeCycleActor> Behavior<T> createChildActor(ActorContext<T> context, T message) {
        info("create guardian parent");
        Optional.ofNullable(getInstance(message)).ifPresent(guardian->
                IntStream.range(0,guardian.numberGuardian()).forEach(index->{
                    var ref = context.spawn(ActorGuardianChild.getInstance(),
                            ApplicationProperties.getInstance().getGuardianName()+index);
                    ref.tell(new InitGuardian());
                })
        );
        return Behaviors.same();
    }

    @Override
    public <T extends LifeCycleActor> Behavior<T> removeChildActor(ActorContext<T> context, T message) {
        return null;
    }
}
