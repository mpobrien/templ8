from org.mob.templ8 import *
#from java.beans import *
from org.apache.commons.beanutils import PropertyUtils

n = NopNode()
print PropertyUtils.getProperty(n, "x")
#for (PropertyDescriptor pd : Introspector.getBeanInfo(Foo.class).getPropertyDescriptors()) {
          #if (pd.getReadMethod() != null && !"class".equals(pd.getName()))
              #System.out.println(pd.getReadMethod().invoke(foo));
              #}

#for pd in Introspector.getBeanInfo(n.getClass()).getPropertyDescriptors() :
    #if pd.getReadMethod() is not None and pd.getName() != 'class':
        #print pd.getName()
