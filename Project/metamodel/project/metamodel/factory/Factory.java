package project.metamodel.factory;

import ro.lrg.xcore.metametamodel.XEntity;
import project.metamodel.entity.*;
import project.metamodel.impl.*;

public class Factory {
   protected static Factory singleInstance = new Factory();
   public static Factory getInstance() { return singleInstance;}
   protected Factory() {}
   private LRUCache<Object, XEntity> lruCache_ = new LRUCache<>(1000);
   public void setCacheCapacity(int capacity) {
       lruCache_.setCapacity(capacity);
   }
   public void clearCache() {lruCache_.clear();}
   public Function createFunction(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new FunctionImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (Function)instance;
    }
   public Project createProject(java.lang.Object obj) {
       XEntity instance = lruCache_.get(obj);
        if (null == instance) {
           instance = new ProjectImpl(obj);
           lruCache_.put(obj, instance);
        }
        return (Project)instance;
    }
}
