Syntax
======

Syntax of entities
==================

```
<Entity> {
    <field> : <Type> { @<Annotation>, ... },
    ...
}
```

with :
- ```<Entity>``` : Entity name
- ```<field>``` : Field name
- ```<Type>``` : Field type
- ```{@<Annotation>, ...}``` : Annotations of the field

```<Entity>``` : Entity name
----------------------------

Replace ```<Entity>``` by the name of the entity.

The file name of this entity must be the name of this entity + file extension ```.entity```


```<field>``` : Field name
--------------------------

Replace ```<field>``` by the name of the field.

The entity fields are separated by a comma ','.

A field has only one type ```<Type>``` and can have none or many annotations ```@<Annotation>``` in curly brackets ```{``` and ```}```.

```<Type>``` : Field types
--------------------------

Replace ```<Type>``` by the field type which can be :

  - **array** of values : ```<Type>[]``` : with ```<Type>``` which can be one of the following types of this list
  - **basic type** : ```Integer```, ```String```, etc.
  - **link** to an another entity : ```<Entity>``` : name of this linked entity
  - **enumeration** : ```#<Enumeration>``` : symbol ```#``` + the name of the enumeration

```@<Annotation>``` : Field annotations
---------------------------------------

Replace ```@<Annotation>``` by the annotation name to define on the field.

The Annotations permit to specify field properties.

The annotations are separated by a comma ','

Syntax of enumerations
======================

```
<Enumeration> {
    <field> = <value>,
    ...
}
```

with :
- ```<Enumeration>``` : Enumeration name
- ```<field>``` : Field name
- ```<value>``` : Field value

```<Enumeration>``` : Enumeration name
--------------------------------------

Replace ```<Enumeration>``` by the name of the enumeration.

The file name of this enumeration must be the name of this enumeration + file extension ```.enum```

```<field>``` : Field name
--------------------------

Replace ```<field>``` by the name of the field.

The fields are separated by a comma ','.

A field has only one value.

```<value>``` : Field value
---------------------------

Replace ```<value>``` by the field value.

Its type can be :
- Number value ```123```
- String value ```"value"```

