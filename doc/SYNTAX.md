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
- ```<field>``` : Field name
- ```<Type>``` : Field type, which can be :
  - an **array** of values : ```<Type>[]```
    - ```<Type>``` can be one of these following types in this list
  - a **basic type** : ```Integer```, ```String```, etc.
  - **link** to an another entity : ```<Entity>```
    - name of this linked entity
  - an **enumeration** : ```#<Enumeration>``` 
    - symbol ```#``` + the name of the enumeration
- ```@<Annotation>``` : **Annotations** to specify field properties
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
