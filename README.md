telosys-dsl-parser
==================
This library is used by TelosysTools to build a model from files. This library allows you to define Entities and Enumerations with links between them.

How-to create a model
==================
All the files defining your model must be in the same directory. You first have to describe your model in a .model file, with this syntax:

    name = ExampleModel
    version = 1.0
    description = The description of the model

Once the model is defined you have to define your entities and enumerations. Each entity/enumeration must be in a single file.
Defining an entity
---------------------------
An entity file has the following syntax:

    Library {
        id : integer {@Id}; // the id
        name : string {@NotNull};
        books : Book[];
        location : string;
        country: #Country
    }

The name of the entity must be the same as the file and start with an upper case. A field of an entity can be:

* A generic type (string, integer, decimal, boolean, date, time, timestamp, blob, clob)
* A link to another entity
* An enumeration (starts with a #)
* An array of value (using [])

Defining an enumeration
------------------------------------
An enumeration file has the following syntax:

    Country : string {
        FR = "France",
        EN = "Great Britain",
        ES = "Spain"
    }

The name of the enumeration must be the same as the file and start with an upper case. The type of the enumeration can be:

* string
* integer
* decimal

If no type is defined the default type is integer.

List of annotations
==================
    @Id - Indicate the primary key of an entity
    @NotNull - Indicate that this field cannot be emty
    @Min() - The minimum value (integer type only), integer parameter required between the parenthesis
    @Max() - The maximum value (integer type only), integer parameter required between the parenthesis
    @SizeMin() - The minimum size of the field, integer parameter required between the parenthesis
    @SizeMax() - The maximum size ot the field, integer parameter required between the parenthesis
    @Past - ?
    @Future - ?

How-to test
===========

1. Clone the project ```git clone https://github.com/Telosys/telosys-dsl-parser```
2. Build the source ```mvn install```
3. Parse a model ```java -jar target/parser-0.0.1-SNAPSHOT-jar-with-dependencies.jar model_example/example.model```


Syntax
======

Syntax of entities
------------------

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

### ```<Entity>``` : Entity name ###

Replace ```<Entity>``` by the name of the entity.

The file name of this entity must be the name of this entity + file extension ```.entity```


### ```<field>``` : Field name ###

Replace ```<field>``` by the name of the field.

The entity fields are separated by a comma ','.

A field has only one type ```<Type>``` and can have none or many annotations ```@<Annotation>``` in curly brackets ```{``` and ```}```.

### ```<Type>``` : Field types ###

Replace ```<Type>``` by the field type which can be :

  - **array** of values : ```<Type>[]``` : with ```<Type>``` which can be one of the following types of this list
  - **basic type** : ```Integer```, ```String```, etc.
  - **link** to an another entity : ```<Entity>``` : name of this linked entity
  - **enumeration** : ```#<Enumeration>``` : symbol ```#``` + the name of the enumeration

### ```@<Annotation>``` : Field annotations ###

Replace ```@<Annotation>``` by the annotation name to define on the field.

The Annotations permit to specify field properties.

The annotations are separated by a comma ','

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

### ```<Enumeration>``` : Enumeration name ###

Replace ```<Enumeration>``` by the name of the enumeration.

The file name of this enumeration must be the name of this enumeration + file extension ```.enum```


### ```<field>``` : Field name ###

Replace ```<field>``` by the name of the field.

The fields are separated by a comma ','.

A field has only one value.


### ```<value>``` : Field value ###

Replace ```<value>``` by the field value.

Its type can be :
- Number value ```123```
- String value ```"value"```

