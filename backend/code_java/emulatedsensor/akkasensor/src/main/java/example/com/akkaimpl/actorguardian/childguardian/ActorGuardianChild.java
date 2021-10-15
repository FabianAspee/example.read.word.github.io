package example.com.akkaimpl.actorguardian.childguardian;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import example.com.akkaimpl.actorsensor.childsensor.ActorSensorImpl;
import example.com.akkaimpl.actorsensor.childsensor.MessageActorSensor;

import java.time.Duration;

public class ActorGuardianChild extends MessageActorGuardianChild {
    private ActorGuardianChild() {
        super();
    }

    public static Behavior<CommandGuardian> getInstance() {
        return Behaviors.setup(ctx -> new ActorGuardianChild().createMsg(ctx));
    }

    @Override
    protected Behavior<CommandGuardian> startGuardian(ActorContext<CommandGuardian> context) {
        return null;
    }
}