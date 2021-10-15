package example.com.akkaimpl.actorsensor.parentsensor;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import example.com.akkaimpl.actorsensor.childsensor.ActorSensorImpl;
import example.com.akkaimpl.actorsensor.childsensor.MessageActorSensor;
import example.com.akkaimpl.configproperties.ApplicationProperties;
import scala.NotImplementedError;

import java.util.stream.IntStream;

public class ActorSensorParent extends MessageActorSensorParent{
    private ActorSensorParent(){
        super();
    }
    public static Behavior<CommandSensorParent> getInstance(final int numberSensor){
        return Behaviors.setup(ctx -> new ActorSensorParent().createMsg(ctx,numberSensor));
    }

    @Override
    public <T extends LifeCycleActor> Behavior<T> createChildActor(ActorContext<T> context, int n) {
        IntStream.range(0,n).forEach(index->{
            var result = context.spawn(ActorSensorImpl.getInstance(),
                    ApplicationProperties.getInstance().getSensorName()+index);
            result.tell(new MessageActorSensor.InitSensor());
        });
        return Behaviors.empty();
    }

    @Override
    public <T extends LifeCycleActor> Behavior<T> removeChildActor(ActorContext<T> context, int n) {
        throw new NotImplementedError();
    }
}
