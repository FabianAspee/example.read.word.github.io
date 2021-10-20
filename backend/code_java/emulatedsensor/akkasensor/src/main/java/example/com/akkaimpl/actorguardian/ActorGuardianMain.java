package example.com.akkaimpl.actorguardian;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import example.com.akkaimpl.actorguardian.parentguardin.ActorGuardianParent;
import example.com.akkaimpl.actorguardian.parentguardin.MessageActorGuardianParent;
import example.com.akkaimpl.configproperties.ApplicationProperties;
import scala.NotImplementedError;

import java.util.Optional;

public final class ActorGuardianMain extends MessageActorGuardianMain {

    public ActorGuardianMain(ActorContext<CommandGuardianMain> ctx) {
        super();
    }

    public static Behavior<CommandGuardianMain> getInstance(){
        return Behaviors.setup(ctx -> new ActorGuardianMain(ctx).createMsg(ctx));
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
        info("create guardian main");
        Optional.ofNullable(getInstance(message)).ifPresent(guardian->{
            var ref = context.spawn(ActorGuardianParent.getInstance(),
                    ApplicationProperties.getInstance().getGuardianName());
            ref.tell(new MessageActorGuardianParent.CreateSensor(guardian.numberGuardian()));
        });
        return Behaviors.same();
    }

    @Override
    public <T extends LifeCycleActor> Behavior<T> removeChildActor(ActorContext<T> context, T message) {
        throw new NotImplementedError();
    }
}
