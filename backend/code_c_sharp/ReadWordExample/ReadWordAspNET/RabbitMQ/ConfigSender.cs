using Newtonsoft.Json;
using RabbitMQ.Client;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReadWordAspNET.RabbitMQ
{
    public class ConfigSender
    {
        private static readonly ConnectionRabbit connectionRabbit = ConnectionRabbit.Instance;
        private static readonly Lazy<ConfigSender> instance = new(() => new ConfigSender());

        private ConfigSender() { }
        public static ConfigSender Instance => instance.Value;
        public void ConvertAndSend(string typeSend, string routingKey, string message)
        {
            var connection = connectionRabbit.connection;
            using var channel = connection.CreateModel();
            channel.ExchangeDeclare(exchange: typeSend, 
                               type: "direct",
                               durable: true);
            channel.BasicPublish(exchange: typeSend,
                                     routingKey: "direct",
                                     basicProperties: null,
                                     body: CreateMessage(message));

        }
        private static byte[] CreateMessage(string message)=> 
            Encoding.UTF8.GetBytes(message);
         
    }
}
