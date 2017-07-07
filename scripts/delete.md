# Delete

### delete all

db.books.deleteMany({});

### deleteMany()

db.books.deleteMany( { edition: {$gt : 2016} } );

### deleteOne()

db.books.deleteOne( { edition: 2002 } );
