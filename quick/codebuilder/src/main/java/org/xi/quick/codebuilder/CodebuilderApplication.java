package org.xi.quick.codebuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.xi.quick.codebuilder.service.GeneratorService;
import org.xi.quick.codebuilder.service.TableService;

import java.util.*;

@SpringBootApplication
public class CodebuilderApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CodebuilderApplication.class, args);
    }

    @Autowired
    TableService tableService;

    @Autowired
    GeneratorService generatorService;

    @Override
    public void run(String... strings) throws Exception {

        Set<String> tableNameSet = tableService.getTableNameList();

        Scanner sc = new Scanner(System.in);

        printUsages();
        while (sc.hasNextLine()) {
            try {
                processLine(sc, tableNameSet);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                printUsages();
            }
        }
    }


    private void processLine(Scanner sc, Set<String> tableNameSet) throws Exception {
        String cmd = sc.next();
        if ("gen".equals(cmd)) {
            String[] args = getArgs(sc);
            if (args.length == 0) return;
            operate(tableNameSet, args, OperateEnum.Generate);
        } else if ("del".equals(cmd)) {
            String[] args = getArgs(sc);
            if (args.length == 0) return;
            operate(tableNameSet, args, OperateEnum.Delete);
        } else if ("quit".equals(cmd) || "q".equals(cmd)) {
            System.exit(0);
        } else {
            System.err.println("[错误] 未知命令:" + cmd);
        }
    }

    private void operate(Set<String> tableNameSet, String[] tables, OperateEnum operateEnum) {

        List<String> tableListNotExist = new ArrayList<>();

        for (String table : tables) {
            if (table.equals("*")) {
                for (String tableName : tableNameSet) {
                    if (operateEnum.equals(OperateEnum.Generate)) {
                        generatorService.generate(tableName);
                    } else {
                        generatorService.delete(tableName);
                    }
                }
                break;
            } else if (table.equals("base")) {
                if (operateEnum.equals(OperateEnum.Generate)) {
                    generatorService.generateAllOnce();
                } else {
                    generatorService.deleteAllOnce();
                }
            } else if (table.equals("aggregate")) {
                if (operateEnum.equals(OperateEnum.Generate)) {
                    generatorService.generateAllOnce();
                } else {
                    generatorService.deleteAllOnce();
                }
            } else if (!tableNameSet.contains(table)) {
                tableListNotExist.add(table);
            } else {
                if (operateEnum.equals(OperateEnum.Generate)) {
                    generatorService.generate(table);
                } else {
                    generatorService.delete(table);
                }
            }
        }

        if (!tableListNotExist.isEmpty()) {
            System.out.println("表" + String.join(",", tableListNotExist) + "不存在");
        }
    }

    private static void printUsages() {
        System.out.println("操作说明:");
        System.out.println("\tgen/del base :                生成/删除基本类型的相关文件");
        System.out.println("\tgen/del aggregate :           生成聚合相关文件");
        System.out.println("\tgen/del * :                   生成/删除所有表的相关文件");
        System.out.println("\tgen/del table [table2...]:    根据表名生成/删除相关文件");
        System.out.println("\texit/quit/q :                 退出");
        System.out.print("请输入命令:");
    }

    String[] getArgs(Scanner sc) {
        String line = sc.nextLine();
        if (line == null) return new String[0];
        StringTokenizer tokenlizer = new StringTokenizer(line, " ");
        List result = new ArrayList();

        while (tokenlizer.hasMoreElements()) {
            Object s = tokenlizer.nextElement();
            result.add(s);
        }
        return (String[]) result.toArray(new String[result.size()]);
    }

    enum OperateEnum {
        Generate,
        Delete
    }
}
