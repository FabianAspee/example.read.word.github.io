package example.com.akkaimpl.actorsensor.childsensor;

import akka.actor.ActorRef;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.remote.WireFormats;

import java.time.Duration;


public class ActorSensorImpl extends MessageActorSensor {
    private ActorSensorImpl(){
        super();
    }
    @Override
    protected Behavior<CommandSensor> startSensor(final ActorContext<CommandSensor> context) {

        context.scheduleOnce(Duration.ofMillis(50),context.getSelf(),new MoveSensor());
        return Behaviors.empty();
    }

    @Override
    protected Behavior<CommandSensor> moveSensor(ActorContext<CommandSensor> context) {
        System.out.println(1);
        return null;
    }

    public static Behavior<CommandSensor> getInstance(){
        return Behaviors.setup(ctx->new ActorSensorImpl().createMsg(ctx));
    }
}
