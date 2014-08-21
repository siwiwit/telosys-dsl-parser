Syntax
======

Syntax of entities
==================

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

Replace ```<Entity>``` by the name of the entity.

The Entity name must be the name of the entity file without the extension '.entity'.

```<field>``` : Field name
--------------------------

Replace ```<field>``` by the name of the field.

The entity fields are separated by a comma ','.

A field has only one type ```<Type>``` and can have none or many annotations ```@<Annotation>``` in curly brackets ```{``` and ```}```.

```<Type>``` : Field types
--------------------------

Replace ```<Type>``` by the field type which can be :

  - an **array** of values : ```<Type>[]```
    - ```<Type>``` can be one of these following types in this list
  - a **basic type** : ```Integer```, ```String```, etc.
  - **link** to an another entity : ```<Entity>```
    - name of this linked entity
  - an **enumeration** : ```#<Enumeration>``` 
    - symbol ```#``` + the name of the enumeration

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
