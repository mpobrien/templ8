from org.mob.templ8 import *
from java.io import *
from java.util import LinkedHashMap
tl = TemplateLoader("/home/mike/templ823/testdata")
#t = tl.getTemplate("fortest.txt")
t = tl.getTemplate("keyvaltest.txt")

for i in range(0,5):
    sw = StringWriter()
    ec = ExecutionContext(sw)
    xs = LinkedHashMap();
    xs.put("mike", 1)
    xs.put("bob", 2)
    xs.put("joe", 3)
    ec.setObject("xs", xs);
    t.execute(ec)
    print "\n\n" + sw.toString()

