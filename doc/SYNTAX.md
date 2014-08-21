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
- ```<Entity>``` : Entity name (it is the name of the file without '.entity')
- ```<field>``` : Field name (each fields are separated by a comma ',')
- ```<Type>``` : Field type (only one type by field)
- ```@<Annotation>``` : Annotations to specify field properties
  - the annotations are separated by a comma ','

Field types
-----------

```<Type>``` : Field type can be :
  - an **array** of values : ```<Type>[]```
    - ```<Type>``` can be one of these following types in this list
  - a **basic type** : ```Integer```, ```String```, etc.
  - **link** to an another entity : ```<Entity>```
    - name of this linked entity
  - an **enumeration** : ```#<Enumeration>``` 
    - symbol ```#``` + the name of the enumeration

Field annotations
-----------------



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
