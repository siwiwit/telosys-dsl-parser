Syntax
======

Syntax of entities
------------------

```
<Entity> {
    <field> : <Type> {@<Annotation>},
    ...
}
```

with :
- ```<Entity>``` : Entity name
- ```<field>``` : Field name (each fields are separated by a comma ',')
- ```<Type>``` : Field type (only one type by field)
- ```@<Annotation>``` : Annotations to specify field properties
  - the annotations are separated by a comma ','

```<Entity>``` : Entity name
----------------------------

It is the name of the entity and of the file too without the extension '.entity'.

```<field>``` : Field name
----------------------------

The entity fields are separated by a comma ','.

A field has only one type ```<Type>``` and can have none or many annotations ```@<Annotation>``` in curly brackets ```{``` and ```}```.

```<Type>``` : Field types
--------------------------

Field type can be :
  - an **array** of values : ```<Type>[]```
    - ```<Type>``` can be one of these following types in this list
  - a **basic type** : ```Integer```, ```String```, etc.
  - **link** to an another entity : ```<Entity>```
    - name of this linked entity
  - an **enumeration** : ```#<Enumeration>``` 
    - symbol ```#``` + the name of the enumeration

```@<Annotation>``` : Field annotations
---------------------------------------

The Annotations permit to specify field properties.
  - the annotations are separated by a comma ','



Syntax of enumerations
----------------------

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
