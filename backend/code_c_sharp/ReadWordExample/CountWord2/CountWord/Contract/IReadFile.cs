using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace CountWord.CountWord.Contract
{
    interface IReadFile
    {
        Task ReadAllFile(string root);

    }
}
