# Update

### updateOne()

db.books.updateOne(
   { isbn: "1234567890" },
   {
     $set: { edition: 2016,
             "authors.0.name": "Paul Chiusanozy"
           },
     $currentDate: { lastModified: true }
   }
);

### updateMany()

db.books.updateMany(
   { edition: {$gt: 2014} },
   {
     $set: { edition: 2016,
             "authors.0.name": "Paul Chiusanozy"
           },
     $currentDate: { lastModified: true }
   }
);

### replaceOne()

db.books.replaceOne(
   { isbn: "1234567890" },
   {  isbn : "1234567890",  title : "Functional Programming in Scala 0", description : "A short description",  edition : 2014, classifications : ["Technology", "Programming"],  editorial : "Manning Pubns Co.", authors : [ { name : "Paul Chiusano", biography: "Has been writing and shipping functional code in Scala since 2008" }, { name : "Runar Bjarnason", biography: "Is a self-taught programmer with 12 years of industry experience" }] }
);


### References

### Update Operators
https://docs.mongodb.com/manual/reference/operator/update/
