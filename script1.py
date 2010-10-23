from org.mob.templ8 import *
from java.io import *
tl = TemplateLoader("/home/mike/templ823/testdata")
t = tl.getTemplate("fortest.txt")
sw = StringWriter()
ec = ExecutionContext(sw)
ec.setObject("xs", [1,2,3,4])
print ec.getObject("xs")
t.execute(ec)



print "\n\n" + sw.toString()
