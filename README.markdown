Goals
======
Templates should be simple, clean, and readable.
Tight integration with the java Map, Set, Iterable, List, Collection paradigm is high priority.
Java Bean conventional syntax can be implicitly used.

Variables
========

Use `{{ ... }}` to evaluate an expression and write the result to the output.

Access elements in a List by index (must be an integer):
    `{{ names[i] }}`

Access elements in a map by key (any object type can be used as a key):
    `{{ usernames['Jim'] }}`

Access properties on an object using Java bean (getter) conventions:

    {{ user.userId }} //this will call user.getUserId()

You can mix and nest these lookups as deep as you want, for example:

    {{ users[loggedInUser.userId].info.firstName }} 
    // this is equivalent to `users.get(loggedInUser.getUserId()).getInfo().getFirstName())

Iterables
========

Loop over any java object that implements `Iterable` with `for` blocks:

     <ul>
     {% for name in users %}
         <li>{{ name }}</li>
     {% endfor %}
     </ul>

You can also iterate over any object that implements java.util.Map, while binding the key and value of each pair to variables inside the contents of the block:

    <ul>
    {% for key, value in dataMap %}
        <li>{{ key }} : {{ value }}</li>
    {% endfor %}
    </ul>

Conditions
==========

Use if-blocks to conditionally execute the contents of a block. You can use multiple elseif or else blocks to test for alternate conditions:

    {% if x == 0 %}
        x is 0.
    {% elseif x == 1 %}
        x is 1.
    {% else %}
        x is... not 0 or 1.
    {% endif %}

You can construct conditions by accessing variables in the same manner described above, using index or key lookups as well as property access.
Conditional Operators
-------------------
Equality: (== and !=) - equivalent to calling the java method `.equals()` and its inverted equivalent.
Inequality: (< > <= >=) Available for all Number objects. The two objects must be of the same class in order to be compared.

Collection Membership:
    Checks if an object is present in the collection.
    For a Set or List object, this condition is the equivalent of calling `xs.contains(x)`.
    For a Map, it's the equivalent of checking for the key, i.e. a call to `xs.containsKey(x)`
    
    ```
    {% if x in xs %} 
        ... 
    {% endif %}
    ```

Variable Test:
Any variable can be used directly as a condition, for example:
    
    `{% if names %} ... {% endif %}`

The condition will be evaluated according to the following rules:
* If it evaluates to a collection, it will test if the collection is empty or not. 
* If it evaluates to a String, it will evaluate to "true" if the string has length greater than zero.
* If the variable is an Integer, it will evaluate to true if the Integer is non-zero.
* In *all* other cases, the expression will evaluate to true of the variable is not-null.


Template Inheritance
===================
Template inheritance works very similar to Django's inheritance system.
First, define blocks that you want to be able to override in a parent template by surrounding them with named "block" tags like this:

parent.html:

    <div>
        <p>some text</p>
        {% block header %}
        <h1>parent block contents!!</h1>
        {% endblock %}
        <p>some more text</p>
    </div>

In another template, reference the parent using the "extends" directive, with the parent's file name:

child.html

    {% extends parent.html %}
    {% block header %}
    <h1>child block contents!!</h1>
    {% endblock %}

The parent template will be evaluated, but replacing each block with the version defined in the child template.
If the child is missing any block, it will default back to evaluating the parent block.
The output rendered from this example will be:

    <div>
        <p>some text</p>
        <h1>child block contents!!</h1>
        <p>some more text</p>
    </div>


And...
=====

lots more to come.
