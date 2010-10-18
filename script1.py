from org.mob.templ8 import *
tl = TemplateLoad("/home/mike/templ823")
t = tl.getTemplate("testfile2.txt")
ec = ExecutionContext()
t.execute(ec)
