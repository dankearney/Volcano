#!/bin/bash
# Add all as collaborators

heroku access:add $1 -r frontend
heroku access:add $1 -r frontend-staging
heroku access:add $1 -r backend
heroku access:add $1 -r backend-staging


