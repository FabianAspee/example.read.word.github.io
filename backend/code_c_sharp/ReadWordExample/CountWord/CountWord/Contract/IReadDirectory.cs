﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace CountWord.CountWord.Contract
{
    public interface IReadDirectory
    {
        void ReadDirectoryRecursive(string path=@"C:\");
    }
}
