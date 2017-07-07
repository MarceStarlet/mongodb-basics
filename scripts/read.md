# Read

### find one

The db.collection.findOne() method also performs a read operation to return a single document. Internally, the db.collection.findOne() method is the db.collection.find() method with a limit of 1.

### find all

db.books.find({});

Use the pretty() method to list all the matched documents in a pretty format of bson.

### find - equality condition

db.books.find({ isbn: "1234567890" });

### find - query operators

db.books.find( { classifications: { $in: [ "Programming", "Development" ] } } );

### find - logical operators (AND, OR)

db.books.find(
 {
     classifications: "Programming",
     $or: [ { edition: { $lt: 2000 } }, { editorial: /^Mann/ } ]
 }
);

### find - embedded documents and arrays

db.books.find( { 'authors.0.name': /^Paul/ } );

db.books.find( { 'authors.name': /^Paul/ } );

db.books.find( { "authors": { $elemMatch: { name: "Runar Bjarnason", biography: /^Is a/ } } } );

### find - projection

db.books.find( { isbn: "1234567890" }, { isbn: 1, title: 1 } );


### References

#### Query operators

https://docs.mongodb.com/manual/reference/operator/query/#query-selectors
