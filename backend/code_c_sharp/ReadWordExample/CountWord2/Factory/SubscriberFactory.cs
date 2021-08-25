using CountWord.CountWord.Contract;
using CountWord.CountWord.Implementation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using count = CountWord.CountWord;

namespace CountWord.Factory
{
    public class SubscriberFactory : ISubscriberFactory
    {
        private static readonly Lazy<ISubscriberFactory> instance = new(() => new SubscriberFactory());
        private SubscriberFactory() { }
        public IReadDirectory GetReadDirectory(Action<string> print) => new ReadDirectory(new count.Implementation.CountWord(5, 10, print));

        public static ISubscriberFactory Instance => instance.Value;
    }
}
