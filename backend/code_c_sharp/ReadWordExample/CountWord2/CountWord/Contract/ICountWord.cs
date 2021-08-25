using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace CountWord.CountWord.Contract
{
    public interface ICountWord
    {
        Task CountWordFork(string[] words);
    }
}
