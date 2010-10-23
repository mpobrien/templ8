from org.mob.templ8 import *
from java.io import *
from java.lang import *

def create_equality_test(op, num1, num2):
    print "testing operator", op, "with numbers: ", num1
    sw = StringWriter()
    bee = BooleanExpressionEvaluator(['x',op,'y'])
    ec = ExecutionContext(sw)
    ec.setObject("x", Integer(num1[0]))
    ec.setObject("y", Integer(num1[1]))
    assert( bee.evaluate(ec) == True );
    print "testing operator", op, " for FALSE, with numbers: ", num2
    ec.setObject("x", Integer(num2[0]))
    ec.setObject("y", Integer(num2[1]))
    assert( bee.evaluate(ec) == False );

create_equality_test('==',[1,1],[1,2])
create_equality_test('!=',[2,1],[1,1])
create_equality_test('>',[2,1],[1,2])
create_equality_test('<',[1,2],[2,1])
create_equality_test('<=',[1,2],[2,1])
create_equality_test('>=',[2,2],[1,2])
create_equality_test('>=',[3,2],[1,2])
# sw = StringWriter()
# # EQUALS
# bee = BooleanExpressionEvaluator(['x','==','y'])
# ec = ExecutionContext(sw)
# ec.setObject("x", Integer(1))
# ec.setObject("y", Integer(1))
# assert( bee.evaluate(ec) == False );
# 
# #LT
# bee = BooleanExpressionEvaluator(['x','<','y'])
# ec = ExecutionContext(sw)
# ec.setObject("x", Integer(1))
# ec.setObject("y", Integer(1))
# assert( bee.evaluate(ec) == False );
# 
# #GT
# bee = BooleanExpressionEvaluator(['x','<','y'])
# ec = ExecutionContext(sw)
# ec.setObject("x", Integer(1))
# ec.setObject("y", Integer(1))
# assert( bee.evaluate(ec) == False );
# 
# #LTE
# bee = BooleanExpressionEvaluator(['x','<','y'])
# ec = ExecutionContext(sw)
# ec.setObject("x", Integer(1))
# ec.setObject("y", Integer(1))
# assert( bee.evaluate(ec) == False );
# 
# #GTE
# bee = BooleanExpressionEvaluator(['x','<','y'])
# ec = ExecutionContext(sw)
# ec.setObject("x", Integer(1))
# ec.setObject("y", Integer(1))
# assert( bee.evaluate(ec) == False );
