using CountWord.CountWord.Util;
using Newtonsoft.Json;
using RabbitMQ.Client;
using ReadWordAspNET.RabbitMQ.ContractConfig;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReadWordAspNET.RabbitMQ
{
    public class ConfigSender:AbstractExecutionContext, IConfigSender
    {
        private readonly IConnectionRabbit connection = ConnectionRabbit.Instance;
        private static readonly Lazy<IConfigSender> instance = new(() => new ConfigSender());
        private ConfigSender() 
        {
            
            connection.GetChannel().ExchangeDeclareNoWait(exchange: ConnectionRabbit.DIRECT_EXCHANGE_NAME,
                               type: "direct",
                               durable: true);
            connection.GetChannel().QueueDeclare(queue: ConnectionRabbit.QUEUE_NAME,
                                durable: true,
                                exclusive: false,
                                autoDelete: false,
                                arguments: null);
        }
        public static IConfigSender Instance => instance.Value;
        public async void ConvertAndSend(string typeSend, string routingKey, string message) => await Task.Run(() =>
          {
              connection.GetChannel().BasicPublish(exchange: typeSend,
                                       routingKey: "direct",
                                       basicProperties: null,
                                       body: CreateMessage(message));

          });
         
        private static byte[] CreateMessage(string message)=> 
            Encoding.UTF8.GetBytes(message);
         
    }
}
