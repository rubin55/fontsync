## Ansible-based fontsync

```sh
# create and use virtual environment
python -m venv .venv
. .venv/bin/activate

# install ansible
pip install -r requirements.txt
```

```sh
# is our inventory found correctly?
ansible --list-host all
  hosts (1):
    local
```

```sh
# run shell command on all hosts
ansible -m shell -a uptime all

# run ansible playbook
ansible-playbook desktop.yml
```
