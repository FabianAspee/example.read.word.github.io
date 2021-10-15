package example.com.akkaimpl.actorguardian.childguardian;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;

public abstract class MessageActorGuardianChild{
protected interface CommandGuardian {}
public static record InitGuardian() implements CommandGuardian {}
    protected Behavior<CommandGuardian> createMsg(final ActorContext<CommandGuardian> context) {
        return Behaviors.receive(CommandGuardian.class)
                .onMessage(InitGuardian.class, start -> startGuardian(context))
                .build();
    }
    protected abstract Behavior<CommandGuardian> startGuardian(final ActorContext<CommandGuardian> context);

}