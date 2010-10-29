import sys
from org.mob.templ8 import *
from java.io import *
from java.util import LinkedHashMap
filename = sys.argv[1]
tl = TemplateLoader("/home/mike/templ823/testdata")
#t = tl.getTemplate("fortest.txt")
t = tl.getTemplate(filename)
sw = StringWriter()
ec = ExecutionContext(sw)
ec.setObject("x1", 1);
ec.setObject("x2", 4);
t.execute(ec)
print "\n\n" + sw.toString()

