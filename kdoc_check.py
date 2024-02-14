import os
import subprocess
import re


# Get the base branch from environment variable
base_branch = os.environ.get("BASE_BRANCH", "master")  # default to 'master' if not set


def run_command(command):
    """Runs a shell command and returns the output."""
    result = subprocess.run(command, stdout=subprocess.PIPE, shell=True, check=True)
    return result.stdout.decode('utf-8')


def get_changed_files():
    """Returns a list of changed .kt files in the PR."""
    command = f"git diff --name-only {base_branch}...HEAD"
    files = run_command(command).splitlines()
    return [f for f in files if f.endswith('.kt')]


def get_file_diff(file_path):
    """Returns the diff of the given file."""
    return run_command(f"git diff origin/main -- {file_path}")


def check_kdoc_in_function(function):
    """Checks if a function has an associated KDoc comment."""
    return re.match(r'\s*/\*\*\n(\s*\*.*\n)+\s*\*/\n\s*fun\s', function)


def main():
    changed_files = get_changed_files()
    missing_kdoc = False

    for file in changed_files:
        diff = get_file_diff(file)
        functions = re.findall(r'(\s*/\*\*.*?fun\s.*?\))', diff, re.DOTALL)

        for function in functions:
            if not check_kdoc_in_function(function):
                print(f"Missing KDoc for function in file: {file}")
                print(function)
                missing_kdoc = True

    if missing_kdoc:
        raise Exception("Some functions are missing KDoc comments.")


if __name__ == "__main__":
    main()
