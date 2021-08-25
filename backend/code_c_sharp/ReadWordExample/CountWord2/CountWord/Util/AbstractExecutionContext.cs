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
            ThreadPool.GetMaxThreads(out int maxThread, out int maxCompletionPortThread);
            ThreadPool.SetMinThreads(minThread,minCompletionPortThread);
            ThreadPool.SetMaxThreads(maxThread, maxCompletionPortThread);
            
        }
    }
}
