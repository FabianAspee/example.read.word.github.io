using RabbitMQ.Client;
using ReadWordAspNET.RabbitMQ.ContractConfig;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ReadWordAspNET.RabbitMQ
{
    public class ConnectionRabbit: IConnectionRabbit, IDisposable
    {
        public static readonly string QUEUE_NAME = "demo-rabbitmq";
        public static readonly string TOPIC_EXCHANGE_NAME = "topic-exchange";
        public static readonly string HEADER_EXCHANGE_NAME = "demo-headers-exchange";
        public static readonly string DIRECT_EXCHANGE_NAME = "demo-direct-exchange";
        public static readonly string FANOUT_EXCHANGE_NAME = "demo-fanout-exchange";
        private static readonly Lazy<IConnectionRabbit> instance = new(() => new ConnectionRabbit());
        public readonly ConnectionFactory factory;
        public readonly IConnection connection;
        public readonly IModel channel;
        private ConnectionRabbit()
        { 
            factory = new ConnectionFactory(){ 
                HostName = "127.0.0.1", 
                Port = 5672, 
                UserName = "guest", 
                Password = "guest" 
            };
            connection = factory.CreateConnection();
            channel = connection.CreateModel();
        }
        public static IConnectionRabbit Instance => instance.Value;
        public void Dispose()
        {
            connection.Dispose();
            channel.Dispose();
        }

        public IModel GetChannel() => channel;
    }
}
