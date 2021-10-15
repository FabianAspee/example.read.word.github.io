package example.com.akkaimpl.actorsensor.childsensor;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;

public class ActorSensorImpl extends MessageActorSensor {
    private ActorSensorImpl(){
        super();
    }
    @Override
    protected Behavior<CommandSensor> startSensor(final ActorContext<CommandSensor> context) {
        System.out.println(1);
        return null;
    }
    public static Behavior<CommandSensor> getInstance(){
        return Behaviors.setup(ctx->new ActorSensorImpl().createMsg(ctx));
    }
}
