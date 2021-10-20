package example.com.akkaimpl.actorpatch.parentpatch;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import example.com.akkaimpl.actorpatch.childpatch.ActorPatchChild;
import example.com.akkaimpl.actorpatch.childpatch.MessageActorPatchChild.InitPatch;
import example.com.akkaimpl.configproperties.ApplicationProperties;
import scala.NotImplementedError;

import java.util.Optional;
import java.util.stream.IntStream;

public class ActorPatchParent extends MessageActorPatchParent{
    private ActorPatchParent(){
        super();
    }
    public static Behavior<CommandPatchParent> getInstance(){
        return Behaviors.setup(ctx -> new ActorPatchParent().createMsg(ctx));
    }

    @Override
    public  CreateSensor getInstance(LifeCycleActor message) {
        if(message instanceof CreateSensor patch){
            return patch;
        }
        return null;
    }

    @Override
    public <T extends LifeCycleActor> Behavior<T> createChildActor(ActorContext<T> context, T message) {
        Optional.ofNullable(getInstance(message)).ifPresent(createPatch ->
                IntStream.range(0,createPatch.numberPatchX()*createPatch.numberPatchY()).forEach(index->{
                    var result = context.spawn(ActorPatchChild.getInstance(),
                            ApplicationProperties.getInstance().getPatchName()+index);
                    result.tell(new InitPatch());
                }));
        return Behaviors.same();
    }

    @Override
    public <T extends LifeCycleActor> Behavior<T> removeChildActor(ActorContext<T> context, T message) {
        throw new NotImplementedError();
    }
}
