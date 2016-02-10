project-new --named orange-crud --stack JAVA_EE_7 --topLevelPackage org.jboss.orange.crud
jpa-setup --provider Hibernate --dbType MYSQL --dataSourceName java:jboss/datasources/ChallengeDS
jpa-new-entity --named User --package org.jboss.orange.crud.model
jpa-new-field --named email --type String
jpa-new-entity --named Buzzword --package org.jboss.orange.crud.model
jpa-new-field --named buzzword --type String
jpa-new-field --named exclude --type boolean
jpa-new-field --named user --type org.jboss.orange.crud.model.User --relationshipType Many-to-One
jpa-new-entity --named URL --package org.jboss.orange.crud.model
jpa-new-field --named url --type String
jpa-new-field --named user --type org.jboss.orange.crud.model.User --relationshipType Many-to-One
jpa-new-entity --named History --package org.jboss.orange.crud.model
jpa-new-field --named timeStamp --type java.util.Date
jpa-new-field --named url --type org.jboss.orange.crud.model.URL --relationshipType Many-to-One
jpa-new-entity --named Statistic --package org.jboss.orange.crud.model
jpa-new-field --named buzzword --type String
jpa-new-field --named instanceCount --type int
jpa-new-field --named frequency --type int
jpa-new-field --named density --type int
jpa-new-field --named history --type org.jboss.orange.crud.model.History --relationshipType Many-to-One
scaffold-setup --provider AngularJS
scaffold-generate --provider AngularJS --generate-rest-resources --targets org.jboss.orange.crud.model.Buzzword org.jboss.orange.crud.model.History org.jboss.orange.crud.model.Statistic org.jboss.orange.crud.model.URL org.jboss.orange.crud.model.User
