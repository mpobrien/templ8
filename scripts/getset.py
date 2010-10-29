from org.mob.templ8 import *
from java.util.regex import *
import string

methodsAtom = "(\w+\.?(\w+)?)+"; // matches aaa.bbb.ccc etc

def get_property_name(x):
    if x.startswith('get'):
        x = string.lower(x[3]) + x[4:]

        

# def issetter(x):
#     if(!method.getName().startsWith("get"))      return false;
#     if(method.getParameterTypes().length != 0)   return false;  
#     if(void.class.equals(method.getReturnType()) return false;
#     return true;
# 
# def printGettersSetters(aClass):
#     methods = aClass.getMethods()
#     for method in methods:
#     for(Method method : methods){
#                     if(isGetter(method)) System.out.println("getter: " + method);
#                         if(isSetter(method)) System.out.println("setter: " + method);
#                           }
#             }
# 
# public static boolean isGetter(Method method){
#           if(!method.getName().startsWith("get"))      return false;
#             if(method.getParameterTypes().length != 0)   return false;  
#               if(void.class.equals(method.getReturnType()) return false;
#                     return true;
#                     }
# 
#               public static boolean isSetter(Method method){
#                     if(!method.getName().startsWith("set")) return false;
#                       if(method.getParameterTypes().length != 1) return false;
#                         return true;
#                         }
