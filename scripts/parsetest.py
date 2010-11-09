from org.mob.templ8 import Atomizer, ExecutionContext
from com.google.common.collect import *;
#Atomizer().atomize("'abcdefg'",0);
#Atomizer().atomize("abcdefg[2]",0);
#Atomizer().getDirective("abcd.efg[a[b]].dde",0);
#Atomizer().getDirective("abcd",0);
a = Atomizer().getDirective("xs[ys[2]]",0);
ec = ExecutionContext();
#ec.setObject("xs",ImmutableList.of("a","b","c"));
#ec.setObject("xs",["a","b","c"]);
ec.setObject("ys",{"a":'Y',2:0,"c":'d'});
ec.setObject("xs",["a","b","c"]);

print a.getValue(ec);
