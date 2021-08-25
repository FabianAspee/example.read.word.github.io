using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace CountWord.CountWord.Util
{
    public abstract class AbstractExecutionContext
    {
        public AbstractExecutionContext()
        {

            ThreadPool.GetMinThreads(out int minThread, out int minCompletionPortThread); 
            ThreadPool.SetMinThreads(minThread,minCompletionPortThread);
            ThreadPool.SetMaxThreads(Environment.ProcessorCount*2, Environment.ProcessorCount);
            
        }
    }
}
