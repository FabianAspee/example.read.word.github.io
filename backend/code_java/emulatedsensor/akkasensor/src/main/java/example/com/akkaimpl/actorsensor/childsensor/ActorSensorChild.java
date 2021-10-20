package example.com.akkaimpl.actorsensor.childsensor;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;

import java.time.Duration;
import java.util.Random;


public class ActorSensorChild extends MessageActorSensor {
    private double posX;
    private double posY;
    private static final double Mt=1.0;
    private static final double  mB = 1.89;
    private static final double vT = 8.98;
    private double Vx=0, Vy=0;
    private static final Random rnd=new Random();
    private static final Object TICK_POSITION = "TickPosition";
    private String address;
    private static final double DECREASE=0.11;
    private static final double MAINTAIN=0.11;
    private static final double KILL=0.001;

    private boolean isReady;
    private ActorSensorChild(){
        super();
    }
    @Override
    protected Behavior<CommandSensor> startSensor(final ActorContext<CommandSensor> context) {
        info("init sensor"+context.getSelf().path().name());
        context.scheduleOnce(Duration.ofMillis(50),context.getSelf(),new MoveSensor());
        return Behaviors.empty();
    }

    @Override
    protected Behavior<CommandSensor> moveSensor(ActorContext<CommandSensor> context) {
        System.out.println(1);
        return null;
    }

    @Override
    protected Behavior<CommandSensor> isReadyPatch() {
        isReady = !isReady;
        return Behaviors.empty();
    }

    public static Behavior<CommandSensor> getInstance(){
        return Behaviors.setup(ctx->new ActorSensorChild().createMsg(ctx));
    }
}
