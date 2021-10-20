package example.com.akkaimpl.actorpatch;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import example.com.akkaimpl.actorpatch.parentpatch.ActorPatchParent;
import example.com.akkaimpl.actorpatch.parentpatch.MessageActorPatchParent;
import example.com.akkaimpl.configproperties.ApplicationProperties;

import java.util.Optional;

public final class ActorPatchMain  extends MessageActorPatchMain {

    public ActorPatchMain(ActorContext<CommandPatchMain> ctx) {
        super();
    }

    public static Behavior<CommandPatchMain> getInstance(){
        return Behaviors.setup(ctx -> new ActorPatchMain(ctx).createMsg(ctx));
    }

    @Override
    public CreateSensor getInstance(LifeCycleActor message) {
        if(message instanceof CreateSensor patch){
            return patch;
        }
        return null;
    }

    @Override
    public <T extends LifeCycleActor> Behavior<T> createChildActor(ActorContext<T> context, T  message) {
        Optional.ofNullable(getInstance(message)).ifPresent(createPatch->{
            var ref = context.spawn(
                    ActorPatchParent.getInstance(),
                    ApplicationProperties.getInstance().getPatchName());
            ref.tell(new MessageActorPatchParent.CreateSensor(createPatch.numberPatchX(), createPatch.numberPatchY()));
        });
        return Behaviors.same();
    }

    @Override
    public <T extends LifeCycleActor> Behavior<T> removeChildActor(ActorContext<T> context, T  message) {
        return null;
    }
}
