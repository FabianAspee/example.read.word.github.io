using CountWord.CountWord.Contract;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace CountWord.Factory
{
    public interface ISubscriberFactory
    { 
        IReadDirectory GetReadDirectory(Action<string> print);
    }
}
