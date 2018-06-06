#!/bin/bash
# Script to set up Volcano's four Heroku remotes

heroku git:remote -a volcano-frontend
git remote rename heroku frontend

heroku git:remote -a volcano-frontend-staging
git remote rename heroku frontend-staging

heroku git:remote -a volcano-backend
git remote rename heroku backend

heroku git:remote -a volcano-backend-staging
git remote rename heroku backend-staging