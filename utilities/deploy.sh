#!/bin/bash
if [ "$1" == "frontend-staging" ]; then
    git push frontend-staging `git subtree split --prefix frontend master`:master  --force
elif [ "$1" == "frontend" ]; then 
    git push frontend `git subtree split --prefix frontend master`:master  --force
elif [ "$1" == "backend-staging" ]; then
    git push backend-staging `git subtree split --prefix backend master`:master  --force
elif [ "$1" == "backend" ]; then 
    git push backend `git subtree split --prefix backend master`:master  --force
else
	echo $1 "not known"
fi
